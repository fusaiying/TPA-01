import request from '@/utils/request'

//# 查询未处理的信息需求数据
export function complaintListAndPublicPool(query) {
  return request({
    url: '/cs/complaint/complaint/selectComplaintAcceptVoList',
    method: 'get',
    params: query
  })
}

//# 查询未处理的信息需求数据
export function complaintListAndPersonalPool(query) {
  return request({
    url: '/cs/complaint/complaint/selectComplaintAcceptVoListOne',
    method: 'get',
    params: query
  })
}


