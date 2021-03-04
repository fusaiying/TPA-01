import request from '@/utils/request'

//案件工作池
export function claimInfoList(query) {
  return request({
    url: '/claimflow/case/appeal/claimInfoList',
    method: 'get',
    params: query
  })
}
