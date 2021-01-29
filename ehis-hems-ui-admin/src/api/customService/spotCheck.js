import request from '@/utils/request'

//# 查询可以质检的工单信息
export function inspectionQueryBy(query) {
  return request({
    //后台接口调用地址
    url: '/cs/spotCheck/internal/inspectionSendData',
    method: 'get',
    params: query
  })
}

//# 查询未处理的质检数据
export function inspectionListAndPublicPool(query) {
  return request({
    url: '/cs/spotCheck/internal/inspectionPublicData',
    method: 'get',
    params: query
  })
}

//# 查询处理中的质检数据
export function inspectionListAndPersonalPool(query) {
  return request({
    url: '/cs/spotCheck/internal/inspectionPersonData',
    method: 'get',
    params: query
  })
}

//任务改派查询改派操作人接口
export function selectSysUser() {
  return request({
    url: '/system/product/selectSysUser',
    method: 'get',
  })
}

//# 查询投诉数据
export function selectConfirmationQuery(query) {
  return request({
    //后台接口调用地址
    url: '/cs/spotCheck/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 发起质检操作
  export function inspectionSendByIds(sendIds) {
    return request({
      url: '/cs/spotCheck/sendIds',
      method: 'put',
      data: sendIds
    })
  }

//# 获取待质检操作
export function inspectionGetByIds(getIds) {
  return request({
    url: '/cs/spotCheck/getIds',
    method: 'put',
    data: getIds
  })
}
