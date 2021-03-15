import request from '@/utils/request'

//案件工作池
export function claimInfoList(query) {
  return request({
    url: '/claimflow/case/appeal/claimInfoList',
    method: 'get',
    params: query
  })
}

//申诉工作池
export function appealList(data) {
  return request({
    url: '/claimmgt/claimAppeal/list',
    method: 'post',
    data: data
  })
}


//新增申诉
export function addAppeal(data) {
  return request({
    url: '/claimmgt/claimAppeal',
    method: 'post',
    data: data
  })
}
