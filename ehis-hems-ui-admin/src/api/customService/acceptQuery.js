import request from '@/utils/request'
//# 查询未处理的信息需求数据
export function selectAcceptQuery(query) {
  return request({
    //后台接口调用地址
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 工单查询
export function selectWorkOrder(params) {
  return request({
    url: '/cs/spotCheck/internal/selectWorkOrder',
    method: 'get',
    params: params
  })
}
//# 工单查询获取
export function updateGetWorkOrder(workOrderNo) {
  return request({
    url: '/cs/spotCheck/internal/updateGetWorkOrder?workOrderNo='+workOrderNo,
    method: 'get',
  })
}

//# 工单查询获取
export function getWorkOrderFlag(workOrderNo) {
  return request({
    url: '/cs/accept/workOrderAccept/'+workOrderNo,
    method: 'get',
  })
}

