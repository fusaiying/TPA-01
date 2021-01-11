<template>
  <div class="app-container">
    <el-card class="box-card" style="margin-top: 10px;">
    <div slot="header" class="clearfix">
      <span>材料清单</span>
      <span style="float: right;">
        <el-button type="primary" size="mini" @click="confirmHandle">补材完毕</el-button>
        <el-button style="float: right;" type="primary" size="mini" @click="goBack">返回</el-button>
      </span>
    </div>
      <el-table
        size="small"
        highlight-current-row
        :header-cell-style="{color:'black',background:'#f8f8ff'}"
        tooltip-effect="dark"
        :data="tableData">
        <el-table-column type="selection" align="center"></el-table-column>
        <el-table-column property="policyNo" label="是否原件" align="center"></el-table-column>
        <el-table-column property="insuredName" label="资料类型" align="center"></el-table-column>
        <el-table-column property="riskName" label="备注" align="center"></el-table-column>
        <el-table-column property="status" label="发起时间" align="center"></el-table-column>
      </el-table>
      <el-form style="margin-top: 20px;" ref="materiForm" :rules="materiFormRules" :model="materiForm" label-width="120px" size="mini">
        <el-row>
          <el-col :span="8">
            <el-form-item label="资料类型：" prop="type">
              <el-select v-model="materiForm.type" placeholder="请选择">
                <el-option label="一" value="1"></el-option>
                <el-option label="二" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否退件：" prop="isReturn">
              <el-select v-model="materiForm.isReturn" placeholder="请选择">
                <el-option label="一" value="1"></el-option>
                <el-option label="二" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="备注：" prop="mark">
              <el-input type="textarea" show-word-limit maxlength="50" :rows="2" v-model="materiForm.mark" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item >
              <el-upload
                class="upload-demo"
                multiple
                action="https://jsonplaceholder.typicode.com/posts/"
                :limit="3"
                :file-list="fileList">
                <el-button size="small" type="success">添加文件</el-button>
                <div slot="tip" class="el-upload__tip" style="color: '#E6A23C">只能上传jpg/png/jpeg文件，且不超过500kb</div>
              </el-upload>
              <p>
                <el-button size="mini" type="primary" style="width: 80px;">点击上传</el-button>
                <el-button size="mini" type="primary" style="width: 80px;">扫描文件</el-button>
              </p>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div>
        <el-row class="row-box">
          <el-col :span="20">已有材料</el-col>
        </el-row>
        <el-table
          size="small"
          highlight-current-row
          :header-cell-style="{color:'black',background:'#f8f8ff'}"
          tooltip-effect="dark"
          :data="tableData">
          <el-table-column align="center" type="index" label="序号" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="name" label="资料类型" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="tasktype" label="文件名称" show-overflow-tooltip>
            <template slot-scope="scope">
              <el-button size="mini" type="text">{{scope.row.tasktype}}</el-button>
            </template>
          </el-table-column>
          <el-table-column align="center" prop="tasktype" label="数量" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="来源" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="是否退件" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="操作人" show-overflow-tooltip></el-table-column>
          <el-table-column align="center" prop="currentstatus" label="补充时间" show-overflow-tooltip></el-table-column>
        </el-table>
      </div>
      <el-form ref="baseForm" :rules="baseFormRules" :model="baseForm" label-width="120px" size="mini">
        <el-row class="row-box">
          <el-col :span="20">退件信息</el-col>
        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="退件人：" prop="addressee">
              <el-input class="item-width" v-model="baseForm.addressee" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="手机：" prop="phone">
              <el-input class="item-width" v-model="baseForm.phone" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="16">
            <el-form-item label="地址：" prop="address">
              <el-input v-model="baseForm.address" autocomplete="off"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="4" style="text-align: right;">
            <el-button size="mini" type="primary" style="width: 80px;" @click="saveHandle">保 存</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'},
      {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}],
      baseForm: {
        returnDate: []
      },
      materiForm: {},
      materiFormRules: {
        isReturn: [{ required: true, message: '不能为空!', trigger: 'change' }],
        type: [{ required: true, message: '不能为空!', trigger: 'change' }],
      },
      tableData: [{policyNo: 'WQE1242134'}],
      baseFormRules: {
        addressee: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        phone: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        address: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        WaybillNo: [{ required: true, message: '不能为空!', trigger: 'blur' }],
        returnDate: [{ required: true, message: '不能为空!', trigger: 'change' }],
      }
    }
  },
  created(){
    // if(this.$route.query){
    //   if(this.$route.query.status==='show') {
    //     this.showFlag = true
    //   }
    // }
  },
  methods: {
    saveHandle(){
      this.$refs.baseForm.validate((valid) => {
        if (valid) {
          alert('submit')
        } else {
          return false;
        }
      })
    },
    confirmHandle(){
      this.$refs.materiForm.validate((valid) => {
        if (valid) {
          alert('submit')
        } else {
          return false;
        }
      })
    },
    goBack(){
      this.$router.go(-1)
    }
  }
}
</script>
<style scoped>
  .item-width {
    width: 200px;
  }
  .row-box {
    padding-top: 20px;
    border-bottom: 1px solid #e6ebf5;
    margin-bottom: 20px;
    line-height: 50px;
  }
</style>
