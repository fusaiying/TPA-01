export default {
  props: {
    imageInfo: {
      type: Object,
      default: () => {
        return {}
      }
    },
    tabLabels: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  data() {
    return {
      activeName1: '00',
      activeName2: '00',
      activeName3: '00',
      activeName4: '00',
      page0: 1,
      page1: 1,
      page2: 1,
      page3: 1,
      activeName: ''
    }
  },
  methods: {
    handleClick(catalog, subtype) {
      this.page0 = 1
      this.page1 = 1
      this.page2 = 1
      this.page3 = 1
      let sub = null
      if (subtype !== '00') {
        sub = subtype
      }
      this.loadtype = catalog
      this.getImageInfo(catalog, 1, sub, true)
      // if (this.activeName !== subtype) {
      this.changeList(catalog)
      // }
      this.activeName = subtype
    },
    changeList(catalog) {
      this.page0 = 1
      this.page1 = 1
      this.page2 = 1
      this.page3 = 1
      this.load1 = false
      this.load2 = false
      this.load3 = false
      this.load4 = false
      this.$emit('changeList', catalog)
    }
    // pageChange(type, catalog, sub, total) {
    //   sub === '00' ? sub = null : sub
    //   if (type === '-') {
    //     if (catalog === '1') {
    //       if (this.page0 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page0--
    //         this.getImageInfo(catalog, this.page0, sub)
    //       }
    //     } else if (catalog === '2') {
    //       if (this.page1 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page1--
    //         this.getImageInfo(catalog, this.page1, sub)
    //       }
    //     } else if (catalog === '3') {
    //       if (this.page2 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page2--
    //         this.getImageInfo(catalog, this.page2, sub)
    //       }
    //     } else if (catalog === '4') {
    //       if (this.page3 === 1) {
    //         return this.$message({ message: '已经是第一页啦！', type: 'warning' })
    //       } else {
    //         this.page3--
    //         this.getImageInfo(catalog, this.page3, sub)
    //       }
    //     }
    //   } else {
    //     if (catalog === '1') {
    //       if (this.page0 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page0++
    //         this.getImageInfo(catalog, this.page0, sub)
    //       }
    //     } else if (catalog === '2') {
    //       if (this.page1 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page1++
    //         this.getImageInfo(catalog, this.page1, sub)
    //       }
    //     } else if (catalog === '3') {
    //       if (this.page2 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page2++
    //         this.getImageInfo(catalog, this.page2, sub)
    //       }
    //     } else if (catalog === '4') {
    //       if (this.page3 >= Math.ceil(total / 5)) {
    //         return this.$message({ message: '没有更多数据啦！', type: 'warning' })
    //       } else {
    //         this.page3++
    //         this.getImageInfo(catalog, this.page3, sub)
    //       }
    //     }
    //   }
    // }
  }
}
