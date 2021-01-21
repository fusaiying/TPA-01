<template>
  <el-dialog
    :visible.sync="dialogVisable"
    :append-to-body="true"
    :before-close="dialogClosed"
    :close-on-click-modal="false"
    title="理赔申请资料"
    width="76%">
    <div style="text-align: right;text-align: right; position: absolute; top: 18px; right: 80px;">
      <el-button size="mini" type="warning" icon="el-icon-plus" @click="download">打包下载</el-button>
    </div>
    <el-row>
      <el-col :span="4">理赔申请书（1）</el-col>
      <el-col :span="20">
        <span v-for="(url, i) in urls" :key="i" class="block">
          <el-image
            :src="url"
            :preview-src-list="urls"
            style="width: 100px; height: 100px; margin-right: 5px;"
            fit="contain"/>
        </span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4">身份证明文件（1）</el-col>
      <el-col :span="20">
        <span v-for="(url, i) in urls" :key="i" class="block">
          <el-image
            :src="url"
            :preview-src-list="urls"
            style="width: 100px; height: 100px; margin-right: 5px;"
            fit="contain"/>
        </span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4">就诊发票（6）</el-col>
      <el-col :span="20">
        <span v-for="(url, i) in urls" :key="i" class="block">
          <el-image
            :src="url"
            :preview-src-list="urls"
            style="width: 100px; height: 100px; margin-right: 5px;"
            fit="contain"/>
        </span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="4">其他（3）</el-col>
      <el-col :span="20">
        <span v-for="(url, i) in urls" :key="i" class="block">
          <el-image
            :src="url"
            :preview-src-list="urls"
            style="width: 100px; height: 100px; margin-right: 5px;"
            fit="contain"/>
        </span>
      </el-col>
    </el-row>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="mini" @click="dialogClosed">关 闭</el-button>
    </div>
  </el-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean
  },
  data() {
    return {
      urls: [
        'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
        'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
        'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
        'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg'
      ]
    }
  },
  computed: {
    dialogVisable: {
      set() {},
      get() {
        return this.value
      }
    }
  },
  methods: {
    dialogClosed() {
      this.$emit('input', false)
    },
    download() {}
  }
}
</script>

<style scoped>
.item-width {
  width: 200px;
}
</style>
