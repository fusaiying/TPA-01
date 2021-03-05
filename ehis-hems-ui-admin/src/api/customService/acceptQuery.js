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
