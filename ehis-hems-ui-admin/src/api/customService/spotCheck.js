import request from '@/utils/request'

//# 查询可以质检的工单信息
export function inspectionQueryBy(query) {
  return request({
    //后台接口调用地址
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 查询未处理的质检数据
export function inspectionListAndPublicPool(query) {
  return request({
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 查询处理中的质检数据
export function inspectionListAndPersonalPool(query) {
  return request({
    url: '/cs/demand/internal/listAndPublicPool',
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
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 发起质检操作
export function inspectionSendByIds(workOrderNos) {
  return request({
    //后台接口调用地址
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: workOrderNos
  })
}
