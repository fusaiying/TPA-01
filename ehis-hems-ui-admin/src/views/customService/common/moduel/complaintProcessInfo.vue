<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>投诉质检处理</span>
    </div>
    <el-form ref="ruleForm" :model="form" style="padding-bottom: 30px;" label-width="100px" label-position="right" size="mini">
      <el-table :header-cell-style="{color:'black',background:'#f8f8ff'}"
                size="small"
                highlight-current-row
                tooltip-effect="dark">
        <div>
          <el-table-column align="center" prop="status" label="质检分类" show-overflow-tooltip/>
          <el-table-column align="center" prop="status" label="质检项目" show-overflow-tooltip/>
          <el-table-column align="center" prop="operateCode" label="是否存在差错" show-overflow-tooltip/>
          <el-table-column align="center" prop="makeBy" label="质检说明" show-overflow-tooltip/>
        </div>
        <div>
          <div>时效性</div>
          <div>
            <el-row>
              <el-input placeholder="投诉件录入时效:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="响应时间:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="根因改善闭环失效:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
          </div>
        </div>
        <div>
          <div>准确性</div>
          <div>
            <el-row>
              <el-input placeholder="受理渠道:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="投诉分类:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="监管计件:" />
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="监管撤诉状态:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row></el-row>
            <el-row>
              <el-input placeholder="投诉原因:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="客户反馈:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="投诉损失:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="投诉根因部门:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="根因改善:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="致诉根因:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="处理结果:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="附件完整性:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="征求处理意见:"/>
              <el-select v-model="form.status" class="item-width" placeholder="请选择">
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="处理意见:"/>
              <el-select v-model="form.status" class="item-width" >
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
            <el-row>
              <el-input placeholder="行协调解或外部鉴定状态:"/>
              <el-select v-model="form.status" class="item-width" >
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
          </div>
        </div>
        <div>
          <div>真实性</div>
          <div>
            <el-row>
              <el-input placeholder="案件真实性:"/>
              <el-select v-model="form.status" class="item-width" >
                <el-option
                  v-for="item in status"
                  :key="item.dictValue"
                  :label="item.dictLabel"
                  :value="item.dictValue"
                ></el-option>
              </el-select>
              <el-input />
            </el-row>
          </div>
        </div>
        <div>
          <div>质检评分：</div>
          <div>
            <el-row>
              <el-input v-model="form.score" placeholder="100分"/>
            </el-row>
          </div>
        </div>
      </el-table>

    </el-form>
  </el-card>
</template>

<script>
  export default {
    name: "complaintProcessInfo",
    props: {
      infohandle: Object
    },
    data(){
      return {

      }
    },
    created() {

    },
    methods: {

    }
  }
</script>

<style scoped>
  .el-tooltip__popper {
    max-width: 400px;
  }

  .el-table /deep/ .el-table__expanded-cell {
    padding: 10px;
  }
</style>





