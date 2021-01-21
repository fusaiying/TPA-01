export default {
  data: () => {
    return {
      // 滚动激活
      activeNav: "",
      tempScrollId: "#anchor-1",
      bounceTime: 0,
    }
  },
  mounted() {
    if(this.node==='report'||this.node==='accept'||this.node==='input'){
      this.activeNav ='#anchor-21'
    } else {
      this.activeNav = '#anchor-1'
    }
    this.$nextTick(function () {
      window.addEventListener("scroll", this.scrollActive)
    })
  },
  beforeDestroy() {
    window.removeEventListener("scroll", this.scrollActive)
  },
  methods: {
    // 锚点跳转
    goAnchor(id) {
      // 获取需要滚动的距离
      const startTop = document.getElementById('start_jump').offsetTop + 60
      // const startTop = document.getElementById('start_jump').offsetTop
      let total = 0
      let distance =
        document.documentElement.scrollTop || document.body.scrollTop
      let step = 5
      if (id !== 'start_jump') {
        total = document.getElementById(id).offsetTop - startTop + 40
        // step = total / 20
        step = total
      } else {
        this.activeNav = "#anchor-1"
      }
      var smoothDown = () => {
        if (distance < total) {
          distance += step
          document.body.scrollTop = distance
          document.documentElement.scrollTop = distance
          setTimeout(smoothDown, 10)
        } else {
          document.body.scrollTop = total
          document.documentElement.scrollTop = total
        }
        if (id !=='start_jump'){
          this.tempScrollId = id
        }
      }
      var smoothUp = () => {
        if (distance > total) {
          distance -= step
          document.body.scrollTop = distance
          document.documentElement.scrollTop = distance
          setTimeout(smoothUp, 10)
        } else {
          document.body.scrollTop = total
          document.documentElement.scrollTop = total
        }
        if (id !== 'start_jump') {
          this.tempScrollId = id
        }
      }
      if (total > distance) {
        smoothDown()
      } else {
        const newTotal = distance - total
        // step = newTotal / 25
        step = newTotal
        smoothUp()
      }
      this.activeNav = this.tempScrollId
    },

    // 滚动鼠标的时候改变锚点颜色
    scrollActive() {
      if (this.tempScrollId) {
        this.activeNav = this.tempScrollId;
      }
      this.bounceTime++
      (id => {
        setTimeout(() => {
          this.bounceTime--
          if (this.bounceTime !== 0) {
            return
          } else {
            let scrolledTop =
              document.documentElement.scrollTop || document.body.scrollTop
            // startTop 调整偏差
            let startTop = document.getElementById("start_jump").offsetTop + 160
            // 报案锚点
            if (this.node==='report') {
              if (
                scrolledTop >=
                document.getElementById("#anchor-21").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-2").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-21"
              }
              else if (
                scrolledTop >
                document.getElementById("#anchor-2").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-12").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-2"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-12").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-10").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-12"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-10").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-14").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-10"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-14").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-16").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-14"
              }  else {
                this.activeNav = "#anchor-16"
              }
            }
            if (this.node === 'accept') {
              if (
                scrolledTop >=
                document.getElementById("#anchor-21").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-2").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-21"
              }
              else if (
                scrolledTop >
                document.getElementById("#anchor-2").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-11").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-2"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-11").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-12").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-11"
              }else if (
                scrolledTop >
                document.getElementById("#anchor-12").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-13").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-12"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-13").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-14").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-13"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-14").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-15").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-14"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-15").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-16").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-15"
              } else {
                this.activeNav = "#anchor-16"
              }
            } 
            if (this.node === 'input') {
              if (
                scrolledTop >=
                document.getElementById("#anchor-2").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-11").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-2"
              }
              else if (
                scrolledTop >
                document.getElementById("#anchor-11").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-12").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-11"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-12").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-13").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-12"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-13").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-14").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-13"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-14").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-19").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-14"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-19").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-16").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-19"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-16").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-17").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-16"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-17").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-17"
              }
            } 
            if (this.node==='firstTrial'||this.node==='review'||this.node ==='complex'||this.node==='spotCheck'||this.node==='correct'||this.node==='physical'){
              if (
                scrolledTop >=
                document.getElementById("#anchor-1").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-3").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-1"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-3").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-4").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-3"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-4").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-5").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-4"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-5").offsetTop -
                startTop &&
                scrolledTop <
                document.getElementById("#anchor-6").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-5"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-6").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-9").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-6"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-9").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-13").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-9"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-13").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-14").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-13"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-14").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-15").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-14"
              } else if (
                scrolledTop >
                document.getElementById("#anchor-15").offsetTop - startTop &&
                scrolledTop <
                document.getElementById("#anchor-16").offsetTop - startTop
              ) {
                this.activeNav = "#anchor-15"
              } else if (this.node ==='physical'){
                if (
                  scrolledTop >
                  document.getElementById("#anchor-16").offsetTop - startTop &&
                  scrolledTop <
                  document.getElementById("#anchor-18").offsetTop - startTop
                ) {
                  this.activeNav = "#anchor-16"
                } else {
                  this.activeNav = "#anchor-18"
                }
              } else {
                if (
                  scrolledTop >
                  document.getElementById("#anchor-16").offsetTop - startTop &&
                  scrolledTop <
                  document.getElementById("#anchor-17").offsetTop - startTop
                ) {
                  this.activeNav = "#anchor-16"
                } else {
                  this.activeNav = "#anchor-17"
                }
              }
            }
            if (id) {
              this.activeNav = id
            }
          }
        }, 100)
      })(this.tempScrollId)
      this.tempScrollId = null
    },

    // 节点映射(前后端有定义了两套节点需要映射)
    mapNode(node){
      switch (node) {
        case 'accept':
          return 'claim_accept'
        case 'scan':
          return 'claim_scan'
        case 'input':
          return 'claim_input'
        case 'firstTrial':
          return 'claim_preliminary_examination'
        case 'review':
          return 'claim_examine'
        case 'complex':
          return 'claim_review'
        case 'spotCheck':
          return 'spot_check'
        case 'correct':
          return 'claim_approve'
        default:
          return node
      }
    }
  }
}
