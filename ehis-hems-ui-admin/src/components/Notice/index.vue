<template>
  <el-dropdown class="right-menu-item hover-effect">
    <el-dropdown-menu></el-dropdown-menu>
    <svg-icon icon-class="notice" class="notice-icon"/>
    <span v-if="messageCount>0" class="tip" style="margin-top: 10px">
      {{messageCount}}
    </span>
  </el-dropdown>
</template>

<script>
  import SockJS from 'sockjs-client'
  import Stomp from 'webstomp-client'
  import {Notification} from 'element-ui'

  export default {
    data() {
      return {
        list1: [],
        messageCount: 12,
        socketUrl: process.env.VUE_APP_BASE_API + '/webSocket'
      }
    },
    mounted() {
      // this.initWebSocket()
    },
    methods: {
      senMessage() {
        this.stompClient.send('/app/hello')
      },
      // 接收到消息并对消息做处理
      onMessageReceived(frame) {
        console.info('Message', frame.body)
        // 收到消息 未读消息数+1
        this.messageCount = Number(this.messageCount) + 1;
        Notification.info({
          title: '通知',
          message: frame.body,
          offset: 35
        })
      },
      // 连接成功
      successCallback() {
        console.info('onConnected')
        this.stompClient.subscribe('/topic/getResponse', this.onMessageReceived)
      },
      initWebSocket() {
        this.socket = new SockJS(this.socketUrl)// 连接服务端
        this.stompClient = Stomp.over(this.socket)
        this.stompClient.connect({}, (frame) => {
          this.successCallback()
        }, () => {
          this.reconnect(this.socketUrl, this.successCallback())
        })
      },
      // 断开重连使用定时器定时连接服务器
      reconnect(socketUrl, successCallback) {
        console.info('in reconnect function')
        let connected = false
        const reconInv = setInterval(() => {
          console.info('in interval' + Math.random())
          this.socket = new SockJS(socketUrl)
          this.stompClient = Stomp.over(this.socket)
          this.stompClient.connect({}, (frame) => {
            console.info('reconnected success')
            // 连接成功，清除定时器
            clearInterval(reconInv)
            connected = true
            successCallback()
          }, () => {
            console.info('reconnect failed')
            console.info('connected:' + connected)
            if (connected) {
              console.info('connect .... what?')
            }
          })
        }, 2000)
      },
    }
  }
</script>

<style>
  .notice-icon {
    height: 25px;
    width: 25px;
    margin-top: 14px
  }

  .tip {
    min-width: 18px;
    height: 18px;
    background: red;
    box-sizing: border-box;
    color: white;
    font-size: 10px;
    text-align: center;
    line-height: 18px;
    padding: 0 5px;
    border-radius: 10px;
    display: inline-block;
    position: absolute;
    top: 0;
    right: 0;
    transform: translate(40%, -20%);
  }

</style>
