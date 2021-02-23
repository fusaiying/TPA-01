import request from '@/utils/request'

//# 征求意见处理页面的池
export function consultationPool(query) {
  return request({
    url: '/cs/from/listNew',
    method: 'get',
    params: query
  })
}

//# 征求意见处理页面的池
export function  complainSearch(workOrderNo) {
  return request({
    url: '/cs/complaint/'+workOrderNo,
    method: 'get',

  })
}
//# 信息需求
export function  dealSearch(data) {
  return request({
    url: '/cs/demand/insertConsultationDemand',
    method: 'put',
    data: data
  })
}

//# 查询信息需求页面数据
export function demandAccept(workOrderNo) {
  return request({
    url: '/cs/demand/accept?workOrderNo=' + workOrderNo,
    method: 'get'
  })
}


//# 投诉
export function  comSearch(data) {
  return request({
    url: '/cs/demand/insertConsultationDemandOne',
    method: 'put',
    data: data
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



