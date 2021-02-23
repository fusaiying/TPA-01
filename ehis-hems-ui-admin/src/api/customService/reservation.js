import request from '@/utils/request'

//#预约页面index的待处理
export function demandListAndPublicPool(query) {
  return request({
    url: '/cs/reservation/internal/listAndPublicPool',
    method: 'get',
    params: query
  })
}
//#预约页面index的处理中
export function demandListAndPersonalPool(query) {
  return request({
    url: '/cs/reservation/internal/listAndPersonalPool',
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

//#获取接口
export function demandObtain(data) {
  return request({
    url: '/cs/demand/obtain',
    method: 'put',
    data: data
  })
}
//#批量获取
export function demandObtainMany(data) {
  return request({
    url: '/cs/demand/many',
    method: 'put',
    data: data
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

//#HMSSearch查询
export function HMSSearch(query) {
  return request({
    url: '/cs/Hms/HmsSearch',
    method: 'get',
    params: query
  })
}
//#轨迹表查询
export function FlowLogSearch(query) {
  return request({
    url: '/cs/FlowLog/searchFlowLog',
    method: 'get',
    params: query
  })
}

//# addDetail页面添加
export function addInsert(data) {
  return request({
    url: '/cs/demand/serviceAdd',
    method: 'put',
    data: data
  })
}




