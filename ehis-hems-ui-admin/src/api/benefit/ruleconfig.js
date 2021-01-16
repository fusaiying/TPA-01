import request from '@/utils/request'

// 查询分配产品字典
export function getProductDict() {
  return request({
    url: '/product/definition/selectproductdict',
    method: 'get',
  })
}
