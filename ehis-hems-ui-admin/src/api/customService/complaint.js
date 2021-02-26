import request from '@/utils/request'

//# 查询待处理的信息需求数据
export function complaintListAndPublicPool(query) {
  return request({
    url: '/cs/complaint/complaint/selectComplaintAcceptVoList',
    method: 'get',
    params: query
  })
}

//# 查询处理中的信息需求数据
export function complaintListAndPersonalPool(query) {
  return request({
    url: '/cs/complaint/complaint/selectComplaintAcceptVoListOne',
    method: 'get',
    params: query
  })
}

//#获取接口
export function complaintObtain(data) {
  return request({
    url: '/cs/demand/obtain',
    method: 'put',
    data: data
  })
}
//#批量获取
export function complaintObtainMany(data) {
  return request({
    url: '/cs/demand/many',
    method: 'put',
    data: data
  })
}
//-----------------------------------------处里页面的
//投诉处理页面
export function complaintDealSubmit(data) {
  return request({
    url: '/cs/complaint/complaintHandling',
    method: 'put',
    data: data
  })
}


//-------------------------------------取消页面
//取消提交
export function complaintCancelSubmit(data) {
  return request({
    url: '/cs/complaint/reservedCancelSubmit',
    method: 'put',
    data: data
  })
}

//# addDetail页面添加
export function complaintAddInsert(data) {
  return request({
    url: '/cs/complaint/serviceAdd',
    method: 'put',
    data: data
  })
}
//-----------------------------修改页面
export function modifyComplaintSubmit(data) {
  return request({
    url: '/cs/complaint/updateComplaintAcceptVo',
    method: 'put',
    data: data
  })
}






