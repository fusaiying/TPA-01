import request from '@/utils/request'
//# 查询未处理的信息需求数据
export function selectCodeDictQuery(query) {
  return request({
    //后台接口调用地址
    url: '/cs/dict/internal/listWithParent',
    method: 'get',
    params: query
  })
}
