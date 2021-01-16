<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>已有材料</span>
      </div>
      <el-table
          size="small"
          highlight-current-row
          :data="tableData1"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="tasktype" label="材料类型" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="tasktype" label="提交时间" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="原件数量" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="复印件数量" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="operator" label="操作人" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button size="small" type="text">{{scope.row.operator}}</el-button>
            </template>
          </el-table-column>
      </el-table>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>影像缩略图</span>
      </div>
      <el-tabs :stretch="true" v-model="activeName1" @tab-click="handleClick(1)">
        <el-tab-pane label="全部（8）" name="1">
          <el-row>
            <el-col v-for="(url, i) in urls" :span="4" :key="i" class="block">
              <el-image
                :src="url"
                style="width: 140px; height: 140px"
                fit="contain"/>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="申请书（2）" name="2"/>
        <el-tab-pane label="身份证（2）" name="3"/>
        <el-tab-pane label="银行卡（4）" name="4"/>
        <el-tab-pane label="发票（0）" name="5"/>
        <el-tab-pane label="清单（0）" name="6"/>
        <el-tab-pane label="病历（0）" name="7"/>
        <el-tab-pane label="其他（0）" name="8"/>
      </el-tabs>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>待补材料</span>
      </div>
      <el-table
          size="small"
          highlight-current-row
          :data="tableData0"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" prop="tasktype" label="材料类型" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="tasktype" label="提交时间" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="补材天数" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="备注" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="操作" show-overflow-tooltip>
            <template slot-scope="">
              <el-button size="small" type="text">上 传</el-button>
              <el-button size="small" type="text">扫 描</el-button>
            </template>
          </el-table-column>
      </el-table>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>补充影像缩略图</span>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button type="primary" size="mini">打印条码</el-button>
          <el-button type="primary" size="mini">批量扫描</el-button>
          <el-button type="primary" size="mini">批量上传</el-button>
          <el-button type="primary" size="mini">影像分类</el-button>
        </span>
      </div>
      <el-tabs :stretch="true" v-model="activeName0" @tab-click="handleClick(0)">
        <el-tab-pane label="全部（8）" name="1">
          <el-row>
            <el-col v-for="(url, i) in urls" :span="4" :key="i" class="block">
              <el-image
                :src="url"
                style="width: 140px; height: 140px"
                fit="contain"/>
            </el-col>
          </el-row>
        </el-tab-pane>
        <el-tab-pane label="申请书（2）" name="2"/>
        <el-tab-pane label="身份证（2）" name="3"/>
        <el-tab-pane label="银行卡（4）" name="4"/>
        <el-tab-pane label="发票（0）" name="5"/>
        <el-tab-pane label="清单（0）" name="6"/>
        <el-tab-pane label="病历（0）" name="7"/>
        <el-tab-pane label="其他（0）" name="8"/>
      </el-tabs>
    </el-card>
    <el-card class="box-card" style="margin-top: 10px;">
      <div slot="header" class="clearfix">
        <span>退件信息</span>
        <span style="position: absolute;right: 40px;top: 0;" >
          <el-button type="primary" size="mini">保存</el-button>
        </span>
      </div>
      <el-form style="padding-bottom: 30px;" ref="baseForm" :model="baseForm" label-width="100px" label-position="right" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="退件方式：" prop="name">
              <el-input class="item-width" clearable v-model="baseForm.way" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="收件人姓名：" prop="name">
              <el-input class="item-width" clearable v-model="baseForm.ratio" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="name">
              <el-input class="item-width" clearable v-model="baseForm.ratio" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="地址：" prop="name">
              <el-input clearable v-model="baseForm.ratio" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注：" prop="name">
              <el-input clearable v-model="baseForm.ratio" size="mini" placeholder="请输入"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table
          size="small"
          highlight-current-row
          :data="tableData0"
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          style="width: 100%;">
          <el-table-column align="center" type="selection" width="50"></el-table-column>
          <el-table-column align="center" prop="tasktype" label="材料类型" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="tasktype" label="提交时间" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="原件数量" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="复印件数量" show-overflow-tooltip></el-table-column>
      </el-table>
    </el-card>
        <el-card class="box-card" style="margin-top: 10px;">
          <div slot="header" class="clearfix">
            <span>补材结论</span>
          </div>
          <el-row>
            <el-form ref="resultForm" :model="resultForm" size="mini" label-width="94px">
              <el-col :span="7">
                <el-form-item
                  :rules="{ required: true, message: '不能为空'}"
                  label="处理结论"
                  prop="result">
                  <el-select v-model="resultForm.result" style="width: 140px;" placeholder="请选择" size="mini">
                    <el-option label="通过" value="1"/>
                    <el-option label="退回" value="2"/>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="17">
                <el-form-item label="结论说明：" prop="desc">
                  <el-input v-model="resultForm.desc" maxlength="1000" show-word-limit clearable size="mini" placeholder="请输入"/>
                </el-form-item>
              </el-col>
              <span style="float: right; padding: 3px 0">
                <el-button type="primary" size="mini" @click="submitHandle">处理完毕</el-button>
                <el-button type="primary" size="mini" @click="goBack">返回</el-button>
              </span>
            </el-form>
          </el-row>
        </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [{tasktype: 'ADEW', operator: 'qwef'}],
      tableData0: [{tasktype: 'ADEW', operator: 'qwef'}],
      tableData1: [{tasktype: 'ADEW', operator: 'qwef'}],
      activeName0: '1',
      activeName1: '1',
      baseForm: {},
      resultForm: {},
      urls: [
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
      ]
    }
  },
  methods: {
    handleClick(type){
      // 请求相关接口
    },
    submitHandle() {},
    goBack() {
      this.$router.go(-1)
    }
  }
};
</script>

<style scoped>
.item-width {
  width: 220px;
}
.el-collapse {
  border: none;
}
.el-tabs /deep/ .el-tabs__item {
  width: 10%;
}
.el-collapse /deep/ .el-collapse-item__header {
  padding-bottom: 10px;
  margin-bottom: 10px;
  color: #555;
  font-weight: 360;
  font-size: 14px;
}
</style>
