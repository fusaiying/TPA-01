import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/sinoutils";

// 查询服务就诊人列表
export function listPersonService(query) {
  return request({
    url: '/system/order/list',
    method: 'post',
    data: query
  })
}

// 查询我的服务就诊人列表
export function listOwnerPersonService(query) {
  return request({
    url: '/system/order/ownerlist',
    method: 'get',
    params: query
  })
}

// 任务获取
export  function taskGetOwner(ordercode){
  return request({
    url: '/system/order/taskGetOwner',
    method: 'get',
    params: {ordercode:ordercode}
  })
}

//任务释放
export function taskRelease(ordercode) {
  return request({
    url: '/system/order/taskRelease',
    method: 'get',
    params: {ordercode:ordercode}
  })
}

// 查询就诊人信息
export function queryPersonService(ordercode) {
  return request({
    url: '/system/order/queryPersonService',
    method: 'get',
    params: {ordercode:ordercode}
  })
}

// 查询服务信息
export function queryServiceOrder(ordercode) {
  return request({
    url: '/system/order/queryServiceOrder',
    method: 'get',
    params: {ordercode:ordercode}
  })
}

// 查询用户列表
export function listUser(query) {
  return request({
    url: '/system/user/list',
    method: 'get',
    params: query
  })
}

// 查询管理部门
export function getManageCom(query){
  return request({
    url: '/system/user/getMangecom',
    method: 'get',
    params: query
  })
}

// 任务指派
export function tasksend(serviceOrder,user) {
  return request({
    url: '/system/order/tasksend',
    method: 'post',
    data: {serviceOrder:serviceOrder,user:user}
  })
}


// 获取服务信息
export function getServiceInfo(ordercode) {
  return request({
    url: "/system/servicedeal/serviceInfo/" + ordercode,
    method: "get",
  })
}

// 新增分诊信息
export function addTrialInfo(query) {
  return request({
    url: "/system/servicedeal/addtrialinfo",
    method: "post",
    data: query,
  })
}

// 新增服务实施
export function addServiceInfo(query) {
  return request({
    url: "/system/servicedeal/addServiceInfo",
    method: "post",
    data: query,
  })
}

// 修改分诊信息
export function updateTrialInfo(query) {
  return request({
    url: "/system/servicedeal/updatetrialinfo",
    method: "put",
    data: query,
  })
}

// 修改服务实施
export function updateServiceInfo(query) {
  return request({
    url: "/system/servicedeal/updateserviceinfo",
    method: "put",
    data: query,
  })
}


