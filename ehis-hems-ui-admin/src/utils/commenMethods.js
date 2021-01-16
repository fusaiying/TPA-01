import moment from 'moment'
import jwt from 'jsonwebtoken'
import Cookies from 'js-cookie'

// 改变日期
export function changeDate(value) {
  return moment(value).format('YYYY-MM-DD')
}

// 表单必选一项校验
export function atLeastOne(value) {
  let flag = false
  for (const key in value) {
    if (typeof value[key] == 'object') {
      if (value[key] && value[key].length) {
        flag = true
      }
    } else {
      if (value[key]) {
        flag = true
      }
    }
  }
  return flag
}

// 前端进行分页（在computed中使用）
export function paginData(tableData, page, size) {
  return tableData.slice((page - 1) * size, page * size)
}

// 获取登陆人员
export function toClient() {
  const username = Cookies.get("username");
/*  const jwt = require('jsonwebtoken')
  const str = jwt.decode(Cookies.get('AUTH-TOEKN'))
  console.log('---------------------------');
  console.log(username);*/
  const name = username
  // window.open(`http://10.252.68.113:30058/claim-easyscan5/EasyScanContainer?usercode=${name}`)
  return name
}

// 获取文件名
export function getFileName(fileName) {
  return fileName.replace(/(.*\/)*([^.]+).*/ig, '$2')
}
// 获取后缀
export function getSuffix(fileName) {
  return fileName.replace(/.+\./, '')
}
// 防抖函数
export function _debounce (fn, delay) {
  let timer = null
  delay = delay || 200

  return function () {
    let args = arguments

    if (timer) {
      clearTimeout(timer)
    }

    timer = setTimeout(function () {
      timer = null
      fn.apply(this, args)
    }.bind(this), delay)
  }
}
// 判断字符串出现的次数
export function patch(str,re){
  var len = str.split(re).length-1
  return len;
}
