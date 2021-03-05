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

//# modify页面保存
export function modifySubmit(data) {
  return request({
    url: '/cs/demand/serviceUpdate',
    method: 'put',
    data: data
  })
}

//# deal页面暂存提交
export function dealADD(data) {
  return request({
    url: '/cs/demand/insertServiceProcessing',
    method: 'put',
    data: data
  })
}

//# 修改说明弹出框
export function modifyDetailsSearch(query) {
  return request({
    url: '/cs/detail/edit',
    method: 'get',
    params: query
  })
}


//# cancel页面保存
export function cancelSubmit(data) {
  return request({
    url: '/cs/demand/cancelSubmit',
    method: 'put',
    data: data
  })
}


//# transfer页面提交
export function transferSubmit(data) {
  return request({
    url: '/cs/by/transferSubmit',
    method: 'post',
    data: data
  })
}

//# coOrganizer页面提交
export function coOrganizerSubmit(data) {
  return request({
    url: '/cs/demand/insertTeamwork',
    method: 'put',
    data: data
  })
}

//# 工单详情页面数据查询
export function orderDetailSearch(query) {
  return request({
    url: '/cs/info3/selectWorkOrder',
    method: 'get',
    params: query
  })
}








