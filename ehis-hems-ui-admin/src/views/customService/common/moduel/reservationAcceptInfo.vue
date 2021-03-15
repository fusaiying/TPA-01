<template>
  <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>服务受理信息</span>
    </div>
    <el-form ref="ruleForm" :model="acceptInfo" style="padding-bottom: 30px;" label-width="100px"
             label-position="right" size="mini">
      <el-row>
        <el-form-item label="受理渠道：" prop="channle" class=".to_right">
          <el-radio-group v-model="acceptInfo.acceptor">
            <el-radio
              v-for="dict in cs_channelOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="服务项目：" prop="Service" class=".to_right">
            <el-select v-model="acceptInfo.acceptor" class="item-width" placeholder="请选择" controls-position="right" :min="0">
              <el-option v-for="item in serves" :key="item.value" :label="item.label"
                         :value="item.value"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人电话：" prop="phone" class=".to_right">
            <el-input v-model="acceptInfo.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="优先级：" prop="priority" class=".to_right">
            <el-select v-model="acceptInfo.acceptor" class="item-width" placeholder="请选择">
              <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="来电人：" prop="phone" class=".to_right">
            <el-input v-model="acceptInfo.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="来电人与被保人关系：" prop="priority">
            <el-select v-model="acceptInfo.acceptor" class="item-width" placeholder="请选择">
              <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人：" prop="lxperson">
            <el-input v-model="acceptInfo.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="联系人性别：" prop="priority">
            <el-select v-model="acceptInfo.contactsPerson.sex" class="item-width">
              <el-option v-for="item in cs_sex" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人与被保人关系：" prop="priority">
            <el-select v-model="acceptInfo.acceptor" class="item-width" placeholder="请选择">
              <el-option v-for="item in serves" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系人语言：" prop="priority">
            <el-select v-model="acceptInfo.contactsLanguage" class="item-width" placeholder="请选择">
              <el-option v-for="item in cs_communication_language" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>

        <el-col :span="8">
          <el-form-item label="联系人移动电话：" prop="phone">
            <el-input v-model="acceptInfo.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>

      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="E-MAIL：" prop="phone">
            <el-input v-model="acceptInfo.acceptor" class="item-width" clearable size="mini" placeholder="请输入"/>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="出单机构：" prop="priority">
            <el-select v-model="acceptInfo.organCode" class="item-width" placeholder="请选择">
              <el-option v-for="item in cs_organization" :key="item.dictValue" :label="item.dictLabel"
                         :value="item.dictValue"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
<!--          <el-form-item label="是否涉及银行转账" prop="bank" >-->
<!--            <el-radio-group v-model="acceptInfo.acceptor">-->
<!--              <el-radio   :label="1">是</el-radio>-->
<!--              <el-radio   :label="2">否</el-radio>-->

<!--            </el-radio-group>-->
<!--          </el-form-item>-->
        </el-col>
      </el-row>
<!--      <el-row>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="开户行：" v-show="ruleForm.bank=='1'" prop="bankaa">-->
<!--            <el-input size="mini" v-model="ruleForm.bankaa"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="开户地：" v-show="ruleForm.bank=='1'" prop="bankbb">-->
<!--            <el-input size="mini" v-model="ruleForm.bankbb"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--        <el-col :span="8">-->
<!--          <el-form-item label="账号：" v-show="ruleForm.bank=='1'" prop="bankcc">-->
<!--            <el-input size="mini" v-model="ruleForm.bankcc"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->

<!--        <el-col :span="8">-->
<!--          <el-form-item label="户名：" v-show="ruleForm.bank=='1'" prop="bankdd">-->
<!--            <el-input size="mini" v-model="ruleForm.bankdd"></el-input>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
<!--      </el-row>-->
      <el-row>
        <el-col :span="16">
          <el-form-item label="业务内容：" prop="textarea">
            <el-input
              type="textarea"
              :rows="2"
              placeholder="请输入内容"
              v-model="acceptInfo.acceptor">
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
  </el-card>

</template>
<script>
let dictss = [{dictType: 'claimType'}, {dictType: 'cs_channel'},]

export default {
  props: {
    acceptInfo: Object
  },
  data() {
    return {
      dutyLoading: false,
      dictList: [],
      serves: [],
      cs_channelOptions: [],
    }
  },
  async mounted() {
    await this.getDictsList(dictss).then(response => {
      this.dictList = response.data
    })
    this.claimTypeOptions = this.dictList.find(item => {
      return item.dictType === 'claimType'
    }).dictDate
    this.cs_channelOptions = this.dictList.find(item => {
      return item.dictType === 'cs_channel'
    }).dictDate
  },
  methods: {}
}
</script>
<style scoped>
.info_span {
  font-size: 13px;
}

.item-width {
  width: 160px;
}

.el-tree /deep/ .el-tree-node__content {
  height: 30px;
}

.check_box {
  margin-top: 16px;
}

.collapse-card.el-card /deep/ .el-card__body {
  padding: 0;
}

.el-collapse /deep/ .el-collapse-item__header {
  background-color: #f8f8f8;
  padding: 0 10px;
}

.el-collapse /deep/ .el-collapse-item__wrap {
  padding: 0 10px;
}

.el-checkbox {
  margin-left: 20px;
}

.el-checkbox-group {
  margin-left: 20px;
}

.el-checkbox-group /deep/ .el-checkbox {
  display: block;
}

.to_right {
  width: 120px;
  text-align: right;
}

/deep/ .custom-tree-node {
  display: flex;
  width: 100%;
  height: 100%;
  flex-direction: column;
}

/deep/ .custom-tree-node span {
  flex: 1;
  display: inline-block;
  width: 80%;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

/deep/ .el-radio{
  /*display: block;*/
  line-height: 30px;
  /*white-space: normal;*/
  /*margin-right: 0;*/
}

</style>
<style>
.el-tooltip__popper {
  max-width: 400px;
}

.el-table /deep/ .el-table__expanded-cell {
  padding: 10px;
}

</style>

