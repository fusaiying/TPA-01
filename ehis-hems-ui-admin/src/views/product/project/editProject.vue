<template>
  <div class="app-container">


    <!-- 步骤条 -->
    <el-steps :active="active" finish-status="success" align-center :space="1000" style="margin-bottom: 20px;">
      <el-step title="项目基础信息"></el-step>
      <el-step title="节点信息"></el-step>
    </el-steps>


    <!-- 最外层 卡片 start -->
    <!-- 表单区域 -->
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px" v-if="this.active == 0">
      <!-- 第一个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-date mr5"></i>
            <span v-if="edit">服务项目新增</span>
            <span v-else>服务项目编辑</span>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务项目编码">
                <el-input
                  v-model="form.projectcode"
                  disabled
                  placeholder="服务项目编码自动生成"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务项目名称" prop="projectname">
                <el-input
                  v-model="form.projectname"
                  placeholder="请输入服务项目名称"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务项目分类" prop="projecttype">
                <el-select v-model="form.projecttype"
                           clearable
                           placeholder="请选择服务项目分类">
                  <el-option
                    v-for="dict in projectTypeOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="价格">
                <el-input-number v-model="form.price" :min="0"></el-input-number>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>
    </el-form>

    <el-form ref="fenzhenform" :model="fenzhenform" :inline="true" label-width="125px" v-if="this.active == 1">
      <!-- 第二个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-info mr5"></i><span>分诊节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="fenzhenform.validflag"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="fenzhenform.endflag"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="fenzhenform.cancelflag"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可回退">
                <el-select v-model="fenzhenform.rollbackflag"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="fenzhenform.processflag"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>


        </el-card>
      </el-row>
    </el-form>

<!--    <el-form ref="peizhenform" :model="peizhenform" :inline="true" label-width="125px" v-if="this.active == 1">-->
<!--      &lt;!&ndash; 第三个卡片 &ndash;&gt;-->
<!--      <el-row class="mb10">-->
<!--        <el-card class="box-card" shadow="hover">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <i class="el-icon-zoom-in mr5"></i><span>陪诊节点</span>-->
<!--          </div>-->

<!--          <el-row>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="该节点是否有效">-->
<!--                <el-select v-model="peizhenform.validflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否有效">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可结束">-->
<!--                <el-select v-model="peizhenform.endflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可结束">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可取消">-->
<!--                <el-select v-model="peizhenform.cancelflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可取消">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->

<!--          <el-row>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可回退">-->
<!--                <el-select v-model="peizhenform.rollbackflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可回退">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="流程条件">-->
<!--                <el-select v-model="peizhenform.processflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择流程条件">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->
<!--        </el-card>-->
<!--      </el-row>-->
<!--    </el-form>-->

<!--    <el-form ref="cirijiuzhenform" :model="cirijiuzhenform" :inline="true" label-width="125px" v-if="this.active == 1">-->
<!--      &lt;!&ndash; 第四个卡片 &ndash;&gt;-->
<!--      <el-row class="mb10">-->
<!--        <el-card class="box-card" shadow="hover">-->
<!--          <div slot="header" class="clearfix">-->
<!--            <i class="el-icon-circle-plus-outline mr5"></i><span>次日就诊节点</span>-->
<!--          </div>-->

<!--          <el-row>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="该节点是否有效">-->
<!--                <el-select v-model="cirijiuzhenform.validflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否有效">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可结束">-->
<!--                <el-select v-model="cirijiuzhenform.endflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可结束">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可取消">-->
<!--                <el-select v-model="cirijiuzhenform.cancelflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可取消">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->

<!--          <el-row>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="是否可回退">-->
<!--                <el-select v-model="cirijiuzhenform.rollbackflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择是否可回退">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">-->
<!--              <el-form-item label="流程条件">-->
<!--                <el-select v-model="cirijiuzhenform.processflag"-->
<!--                           clearable-->
<!--                           placeholder="请选择流程条件">-->
<!--                  <el-option-->
<!--                    v-for="dict in yesNoOptions"-->
<!--                    :key="dict.dictValue"-->
<!--                    :label="dict.dictValue + ' - ' +dict.dictLabel"-->
<!--                    :value="dict.dictValue"-->
<!--                  ></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </el-col>-->
<!--          </el-row>-->

<!--        </el-card>-->
<!--      </el-row>-->
<!--    </el-form>-->

    <el-form ref="shishiform" :model="shishiform" :inline="true" label-width="125px" v-if="this.active == 1">
      <!-- 第五个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-video-camera mr5"></i><span>记录服务实施节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="shishiform.validflag"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="shishiform.endflag"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="shishiform.cancelflag"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可回退">
                <el-select v-model="shishiform.rollbackflag"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="shishiform.processflag"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

        </el-card>
      </el-row>
    </el-form>

    <el-form ref="huifangform" :model="huifangform" :inline="true" label-width="125px" v-if="this.active == 1">
      <!-- 第六个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-help mr5"></i><span>回访节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="huifangform.validflag"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="huifangform.endflag"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="huifangform.cancelflag"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可回退">
                <el-select v-model="huifangform.rollbackflag"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="huifangform.processflag"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

        </el-card>
      </el-row>
    </el-form>

    <el-form ref="wentiform" :model="wentiform" :inline="true" label-width="125px" v-if="this.active == 1">
      <!-- 第七个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-error mr5"></i>
            <span>问题工单</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="wentiform.validflag"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="wentiform.endflag"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="wentiform.cancelflag"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可回退">
                <el-select v-model="wentiform.rollbackflag"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="wentiform.processflag"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in yesNoOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

        </el-card>
      </el-row>
    </el-form>

    <el-row style="text-align: right" v-if="this.active == 0">
      <el-col>
        <el-button icon="el-icon-arrow-right" size="mini" @click="nextStep">下一步</el-button>
        <el-button icon="el-icon-d-arrow-left" size="mini" @click="backFun">返回</el-button>
      </el-col>
    </el-row>
    <el-row style="text-align: right;" v-if="this.active == 1">
      <el-col class="mt10 mb10">
        <el-button icon="el-icon-arrow-left" size="mini" @click="preStep">上一步</el-button>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="submit"
        >保存
        </el-button>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="submitAnd"
        >保存并发布
        </el-button>
        <el-button icon="el-icon-d-arrow-left" size="mini" @click="backFun">返回</el-button>
      </el-col>
    </el-row>

    <!-- end 最外层 卡片 -->
  </div>
</template>

<script>
  import {
    saveProject,
    queryProjectData
  } from "@/api/product/project";


  export default {
    name: "editProject",
    data() {
      return {
        active: 0,
        form: {
          projectcode: undefined,
          projectname: undefined,
          projecttype: undefined
        },
        fenzhenform: {
          nodecode: "fenzhen",
          nodename: "分诊节点",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        peizhenform: {
          nodecode: "peizhen",
          nodename: "陪诊节点",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        cirijiuzhenform: {
          nodecode: "cirijiuzhen",
          nodename: "次日就诊节点",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        shishiform: {
          nodecode: "fuwushishi",
          nodename: "记录服务实施节点",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        huifangform: {
          nodecode: "huifang",
          nodename: "回访节点",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        wentiform: {
          nodecode: "wentigongdan",
          nodename: "问题工单",
          validflag: undefined,
          endflag: undefined,
          cancelflag: undefined,
          rollbackflag: undefined,
          processflag: undefined
        },
        rules: {
          projectname: [
            {required: true, message: '服务项目名称必填', trigger: 'blur'},
          ],
          projecttype: [
            {required: true, message: '服务项目分类必填', trigger: 'change'},
          ],
        },
        projectNodeList: [],
        // 项目分类
        projectTypeOptions: [],
        // 是否有效
        yesNoOptions: [],
        edit: false,
      }
    },
    created() {
      // 获取服务产品编码
      const projectcode = this.$route.params && this.$route.params.projectcode;
      this.form.projectcode = projectcode;
      // 如果没有项目编码说明是新增
      if (this.form.projectcode == undefined || this.form.projectcode == "") {
        this.edit = true;
      } else {
        this.getProjectData(projectcode);
      }

      this.getDicts("product_service_imple_type").then(response => {
        this.projectTypeOptions = response.data;
      });

      // 根据服务产品编码查询服务产品
      this.getDicts("sys_yes_no").then(response => {
        this.yesNoOptions = response.data;
      });
    },
    methods: {
      getProjectData(projectcode) {
        queryProjectData(projectcode).then(response => {
          if (response.code === 200) {
            response.data.forEach(item => {
              if (item.nodecode == "fenzhen") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.fenzhenform.nodecode = item.nodecode;
                this.fenzhenform.nodename = item.nodename;
                this.fenzhenform.validflag = item.validflag;
                this.fenzhenform.endflag = item.endflag;
                this.fenzhenform.cancelflag = item.cancelflag;
                this.fenzhenform.rollbackflag = item.rollbackflag;
                this.fenzhenform.processflag = item.processflag;
              }
              if (item.nodecode == "peizhen") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.peizhenform.nodecode = item.nodecode;
                this.peizhenform.nodename = item.nodename;
                this.peizhenform.validflag = item.validflag;
                this.peizhenform.endflag = item.endflag;
                this.peizhenform.cancelflag = item.cancelflag;
                this.peizhenform.rollbackflag = item.rollbackflag;
                this.peizhenform.processflag = item.processflag;
              }
              if (item.nodecode == "cirijiuzhen") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.cirijiuzhenform.nodecode = item.nodecode;
                this.cirijiuzhenform.nodename = item.nodename;
                this.cirijiuzhenform.validflag = item.validflag;
                this.cirijiuzhenform.endflag = item.endflag;
                this.cirijiuzhenform.cancelflag = item.cancelflag;
                this.cirijiuzhenform.rollbackflag = item.rollbackflag;
                this.cirijiuzhenform.processflag = item.processflag;
              }
              if (item.nodecode == "fuwushishi") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.shishiform.nodecode = item.nodecode;
                this.shishiform.nodename = item.nodename;
                this.shishiform.validflag = item.validflag;
                this.shishiform.endflag = item.endflag;
                this.shishiform.cancelflag = item.cancelflag;
                this.shishiform.rollbackflag = item.rollbackflag;
                this.shishiform.processflag = item.processflag;
              }
              if (item.nodecode == "huifang") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.huifangform.nodecode = item.nodecode;
                this.huifangform.nodename = item.nodename;
                this.huifangform.validflag = item.validflag;
                this.huifangform.endflag = item.endflag;
                this.huifangform.cancelflag = item.cancelflag;
                this.huifangform.rollbackflag = item.rollbackflag;
                this.huifangform.processflag = item.processflag;
              }
              if (item.nodecode == "wentigongdan") {
                this.form.projectcode = item.projectcode;
                this.form.projectname = item.projectname;
                this.form.projecttype = item.projecttype;
                this.wentiform.nodecode = item.nodecode;
                this.wentiform.nodename = item.nodename;
                this.wentiform.validflag = item.validflag;
                this.wentiform.endflag = item.endflag;
                this.wentiform.cancelflag = item.cancelflag;
                this.wentiform.rollbackflag = item.rollbackflag;
                this.wentiform.processflag = item.processflag;
              }
            });
          }
        });
      },

      submit() {
        this.fenzhenform.projectcode = this.form.projectcode;
        this.fenzhenform.projectname = this.form.projectname;
        this.fenzhenform.projecttype = this.form.projecttype;
        this.fenzhenform.status = "01";
        this.peizhenform.projectcode = this.form.projectcode;
        this.peizhenform.projectname = this.form.projectname;
        this.peizhenform.projecttype = this.form.projecttype;
        this.peizhenform.status = "01";
        this.cirijiuzhenform.projectcode = this.form.projectcode;
        this.cirijiuzhenform.projectname = this.form.projectname;
        this.cirijiuzhenform.projecttype = this.form.projecttype;
        this.cirijiuzhenform.status = "01";
        this.shishiform.projectcode = this.form.projectcode;
        this.shishiform.projectname = this.form.projectname;
        this.shishiform.projecttype = this.form.projecttype;
        this.shishiform.status = "01";
        this.huifangform.projectcode = this.form.projectcode;
        this.huifangform.projectname = this.form.projectname;
        this.huifangform.projecttype = this.form.projecttype;
        this.huifangform.status = "01";
        this.wentiform.projectcode = this.form.projectcode;
        this.wentiform.projectname = this.form.projectname;
        this.wentiform.projecttype = this.form.projecttype;
        this.wentiform.status = "01";
        this.projectNodeList.length = 0;
        this.projectNodeList.push(this.fenzhenform);
        this.projectNodeList.push(this.peizhenform);
        this.projectNodeList.push(this.cirijiuzhenform);
        this.projectNodeList.push(this.shishiform);
        this.projectNodeList.push(this.huifangform);
        this.projectNodeList.push(this.wentiform);
        saveProject(this.projectNodeList).then(response => {
          if (response.code === 200) {
            if (this.form.projectcode == undefined) {
              this.form.projectcode = response.msg;
              this.msgSuccess("保存成功");
            } else {
              this.msgSuccess("修改成功");
            }
          } else {
            this.msgError(response.msg);
          }
        });
      },
      submitAnd() {
        this.fenzhenform.projectcode = this.form.projectcode;
        this.fenzhenform.projectname = this.form.projectname;
        this.fenzhenform.projecttype = this.form.projecttype;
        this.fenzhenform.status = "02";
        this.peizhenform.projectcode = this.form.projectcode;
        this.peizhenform.projectname = this.form.projectname;
        this.peizhenform.projecttype = this.form.projecttype;
        this.peizhenform.status = "02";
        this.cirijiuzhenform.projectcode = this.form.projectcode;
        this.cirijiuzhenform.projectname = this.form.projectname;
        this.cirijiuzhenform.projecttype = this.form.projecttype;
        this.cirijiuzhenform.status = "02";
        this.shishiform.projectcode = this.form.projectcode;
        this.shishiform.projectname = this.form.projectname;
        this.shishiform.projecttype = this.form.projecttype;
        this.shishiform.status = "02";
        this.huifangform.projectcode = this.form.projectcode;
        this.huifangform.projectname = this.form.projectname;
        this.huifangform.projecttype = this.form.projecttype;
        this.huifangform.status = "02";
        this.wentiform.projectcode = this.form.projectcode;
        this.wentiform.projectname = this.form.projectname;
        this.wentiform.projecttype = this.form.projecttype;
        this.wentiform.status = "02";
        this.projectNodeList.length = 0;
        this.projectNodeList.push(this.fenzhenform);
        this.projectNodeList.push(this.peizhenform);
        this.projectNodeList.push(this.cirijiuzhenform);
        this.projectNodeList.push(this.shishiform);
        this.projectNodeList.push(this.huifangform);
        this.projectNodeList.push(this.wentiform);
        saveProject(this.projectNodeList).then(response => {
          if (response.code === 200) {
            if (this.form.projectcode == undefined) {
              this.form.projectcode = response.msg;
              this.msgSuccess("保存成功");
            } else {
              this.msgSuccess("修改成功");
            }
          } else {
            this.msgError(response.msg);
          }
        });
      },
      backFun() {
        this.$store.dispatch("tagsView/delView", this.$route);
        this.$router.back();
      },

      // 下一步按钮
      nextStep() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.active = 1;
          }
        });
      },
      // 上一步
      preStep() {
        this.active = 0;
      },
    },
  }
</script>

<style scoped>

</style>
