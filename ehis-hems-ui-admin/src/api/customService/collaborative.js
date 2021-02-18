import request from '@/utils/request'
//# 查询协办工作池
export function selectAssist(query) {
  return request({
    url: '/cs/demand/selectAssist',
    method: 'get',
    params: query
  })
}

