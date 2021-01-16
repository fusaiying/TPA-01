<template>
  <div class="app-container">
    <!-- 最外层 卡片 start -->
    <!-- 表单区域 -->
    <el-form ref="form" :model="form" :rules="rules" :inline="true" label-width="125px">

      <!-- 第一个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-date mr5"></i><span>服务流程基本信息</span>
          </div>
          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="服务流程名称">
                <el-input
                  v-model="form.serviceProcess"
                  placeholder="请输入服务流程名称"
                  clearable></el-input>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="支持的渠道" prop="channel">
                <el-select v-model="form.channel"
                           clearable
                           placeholder="请选择支持的渠道">
                  <el-option
                    v-for="dict in channel"
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


      <!-- 第二个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-info mr5"></i><span>分诊节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="form.effective"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel"
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
                <el-select v-model="form.back"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in process"
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

      <!-- 第三个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-zoom-in mr5"></i><span>陪诊节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="form.effective1"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective1"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end1"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end1"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel1"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel1"
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
                <el-select v-model="form.back1"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back1"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process1"
                           clearable
                           placeholder="请选择流程条件">
                  <el-options
                    v-for="dict in process1"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-options>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-card>
      </el-row>


      <!-- 第四个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-circle-plus-outline mr5"></i><span>次日就诊节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="form.effective2"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective2"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end2"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end2"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel2"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel2"
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
                <el-select v-model="form.back2"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back2"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process2"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in process2"
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

      <!-- 第五个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-video-camera mr5"></i><span>记录服务实施节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="form.effective3"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective3"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end3"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end3"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel3"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel3"
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
                <el-select v-model="form.back3"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back3"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process3"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in process3"
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

      <!-- 第六个卡片 -->
      <el-row class="mb10">
        <el-card class="box-card" shadow="hover">
          <div slot="header" class="clearfix">
            <i class="el-icon-help mr5"></i><span>回访节点</span>
          </div>

          <el-row>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="该节点是否有效">
                <el-select v-model="form.effective4"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective4"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end4"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end4"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel4"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel4"
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
                <el-select v-model="form.back4"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back4"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process4"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in process4"
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
                <el-select v-model="form.effective5"
                           clearable
                           placeholder="请选择是否有效">
                  <el-option
                    v-for="dict in effective5"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可结束">
                <el-select v-model="form.end5"
                           clearable
                           placeholder="请选择是否可结束">
                  <el-option
                    v-for="dict in end5"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="是否可取消">
                <el-select v-model="form.cancel5"
                           clearable
                           placeholder="请选择是否可取消">
                  <el-option
                    v-for="dict in cancel5"
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
                <el-select v-model="form.back5"
                           clearable
                           placeholder="请选择是否可回退">
                  <el-option
                    v-for="dict in back5"
                    :key="dict.dictValue"
                    :label="dict.dictValue + ' - ' +dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
              <el-form-item label="流程条件">
                <el-select v-model="form.process5"
                           clearable
                           placeholder="请选择流程条件">
                  <el-option
                    v-for="dict in process5"
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

      <el-row style="text-align: right;">
        <el-col class="mt10 mb10">
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

    </el-form>

    <!-- end 最外层 卡片 -->
  </div>
</template>

<script>
  export default {
    name: "serviceProcess",
    data() {
      return {
        form: {},
        rules: {
          channel: [
            {required: true, message: "支持的渠道不能为空", trigger: "change"}
          ],
        },
        // 支持的渠道
        channel: [],
        // 是否有效
        effective: [],
        effective1: [],
        effective2: [],
        effective3: [],
        effective4: [],
        effective5: [],

        // 是否结束
        end: [],
        end1: [],
        end2: [],
        end3: [],
        end4: [],
        end5: [],

        // 是否取消
        cancel: [],
        cancel1: [],
        cancel2: [],
        cancel3: [],
        cancel4: [],
        cancel5: [],

        // 是否可回退
        back: [],
        back1: [],
        back2: [],
        back3: [],
        back4: [],
        back5: [],

        // 流程条件
        process: [],
        process1: [],
        process2: [],
        process3: [],
        process4: [],
        process5: [],

      }
    },
    created() {
      this.getDicts("sys_yes_no").then(response => {
        this.effective = response.data;
        this.effective1 = response.data;
        this.effective2 = response.data;
        this.effective3 = response.data;
        this.effective4 = response.data;
        this.effective5 = response.data;
        this.end = response.data;
        this.end1 = response.data;
        this.end2 = response.data;
        this.end3 = response.data;
        this.end4 = response.data;
        this.end5 = response.data;
        this.cancel = response.data;
        this.cancel1 = response.data;
        this.cancel2 = response.data;
        this.cancel3 = response.data;
        this.cancel4 = response.data;
        this.cancel5 = response.data;
        this.back = response.data;
        this.back1 = response.data;
        this.back2 = response.data;
        this.back3 = response.data;
        this.back4 = response.data;
        this.back5 = response.data;
      });
    },
    methods: {
      submit() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.$message({
              showClose: true,
              message: '保存成功',
              type: 'success'
            });
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
          ;
        });
      },
      submitAnd() {
        // 表单为空校验
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.$message({
              showClose: true,
              message: '保存并发布成功',
              type: 'success'
            });
          } else {
            this.$message({
              message: '请完成必填项，再进行保存',
              type: 'warning'
            });
          }
          ;
        });
      },
      backFun() {
        this.$router.push({path: '/service/configure/process'});
      },

    },
  }
</script>

<style scoped>

</style>
