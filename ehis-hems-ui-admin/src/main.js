import Vue from 'vue'
import dict from './components/Dict'
import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './assets/styles/element-variables.scss'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/sino.scss' // sino css
import App from './App'
import store from './store'
import router from './router'
import permission from './directive/permission'
import {download} from '@/utils/request'
Vue.use(dict)
import './assets/icons' // icon
import './permission' // permission control
import {getDicts} from "@/api/system/dict/data";
import {getDictsList} from "@/api/system/dict/data";
import {selectContinent} from "@/api/system/dict/data";
import {selectCountryByContinent} from "@/api/system/dict/data";
import {selectPlaceCodeByPlaceName} from "@/api/system/dict/data";
import {getConfigKey} from "@/api/system/config";
import {parseTime, resetForm, addDateRange, selectDictLabel, selectDictLabels, handleTree} from "@/utils/sinoutils";
import Pagination from "@/components/Pagination";
import i18n from './lang'
//自定义表格工具扩展
import RightToolbar from "@/components/RightToolbar"
//导入条形码插件
import VueBarcode from '@xkeshi/vue-barcode'

Vue.component('barcode',VueBarcode)
// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getDictsList = getDictsList
Vue.prototype.selectContinent = selectContinent
Vue.prototype.selectCountryByContinent = selectCountryByContinent
Vue.prototype.selectPlaceCodeByPlaceName = selectPlaceCodeByPlaceName
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree

Vue.prototype.msgSuccess = function (msg) {
  this.$message({showClose: true, message: msg, type: "success"});
}

Vue.prototype.msgError = function (msg) {
  this.$message({showClose: true, message: msg, type: "error"});
}

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
}

// 全局组件挂载
Vue.component('Pagination', Pagination)

Vue.use(permission)

Vue.component('RightToolbar', RightToolbar)

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
  i18n: (key, value) => i18n.t(key, value)
})

Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  i18n,
  render: h => h(App)
})
