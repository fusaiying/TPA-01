export default {
  data() {
    return {
      imgIdx: 0,
      transform: {
        scale: 1,
        deg: 0,
        offsetX: 0,
        offsetY: 0,
        enableTransition: false
      }
    }
  },
  computed: {
    imgStyle() {
      const { scale, deg, offsetX, offsetY, enableTransition } = this.transform
      const style = {
        transform: `scale(${scale}) rotate(${deg}deg)`,
        transition: enableTransition ? 'transform .3s' : '',
        'margin-left': `${offsetX}px`,
        'margin-top': `${offsetY}px`
      }
      return style
    }
  },
  methods: {
    mousedownHandle(event) {
      const wrap = document.getElementById('wrap')
      event.preventDefault()
      const disX = event.clientX - box.offsetLeft
      const disY = event.clientY - box.offsetTop
      wrap.onmousemove = function(event) {
        event.preventDefault()
        let x = event.clientX - disX
        let y = event.clientY - disY
        // 设置移动边界值
        x = x <= 0 ? 0 : x
        x = x >= wrap.offsetWidth + box.offsetWidth / 2 - box.offsetWidth ? wrap.offsetWidth + box.offsetWidth / 2 - box.offsetWidth : x
        y = y <= 0 ? 0 : y
        y = y >= wrap.offsetHeight + box.offsetWidth / 2 - box.offsetHeight ? wrap.offsetHeight + box.offsetWidth / 2 - box.offsetHeight : y
        box.style.left = x + 'px'
        box.style.top = y + 'px'
      }
      wrap.onmouseleave = function() {
        wrap.onmousemove = null
        wrap.onmouseup = null
      }
      wrap.onmouseup = function() {
        wrap.onmousemove = null
        wrap.onmouseup = null
      }
    },
    handleActions(action) {
      const { zoomRate, rotateDeg, enableTransition } = {
        zoomRate: 0.2,
        rotateDeg: 90,
        enableTransition: true
      }
      const { transform } = this
      switch (action) {
        case 'zoomOut':
          if (transform.scale > 0.2) {
            transform.scale = parseFloat((transform.scale - zoomRate).toFixed(3))
          }
          break
        case 'zoomIn':
          transform.scale = parseFloat((transform.scale + zoomRate).toFixed(3))
          break
        case 'clocelise':
          transform.deg += rotateDeg
          break
      }
      transform.enableTransition = enableTransition
    },
    // changeImage(url,type,flag, i) {
    //   this.src = url
    //   this.imgIdx = i + 1
    //   this.selType = type
    //   this.form.checked = flag
    //   // if (this.node==='classificat') {
    //   //   this.$refs.checkImage[i].model = !this.$refs.checkImage[i].model
    //     // if (this.$refs.checkImage[i].model === false) {
    //     //   this.imgIdx = 0
    //     // }
    //   // }
    // },
    // preViewImage(type) {
    //   if (type === 'pre') {
    //     this.imgIdx--
    //     this.src = this.urls[this.imgIdx - 1].src
    //     this.selType = this.urls[this.imgIdx - 1].pageType
    //     this.form.checked = this.urls[this.imgIdx - 1].originals
    //   } else {
    //     this.imgIdx++
    //     this.src = this.urls[this.imgIdx - 1].src
    //     this.selType = this.urls[this.imgIdx - 1].pageType
    //     this.form.checked = this.urls[this.imgIdx - 1].originals
    //   }
    // }
  }
}
