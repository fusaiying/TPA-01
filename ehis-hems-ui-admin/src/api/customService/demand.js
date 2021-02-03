import request from '@/utils/request'

//# 查询未处理的信息需求数据
export function demandListAndPublicPool(query) {
  return request({
    url: '/cs/demand/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}

//# 查询未处理的信息需求数据
export function demandListAndPersonalPool(query) {
  return request({
    url: '/cs/demand/internal/listAndPersonalPool',
    method: 'get',
    params: query
  })
}
//#二次来电查询
export function selectCallAgain() {
  return request({
    url: '/cs/again/selectCallAgain',
    method: 'get',
  })
}

//#处理页面新增
export function dealAdd(data) {
  return request({
    url: '/cs/demand/insertServiceProcessing',
    method: 'put',
    data: data
  })
}
