import request from '@/utils/request'

// 查询base_supplier_info（供应商基础信息）列表
export function listInfo(query) {
  return request({
    url: '/provider/info/list',
    method: 'get',
    params: query
  })
}

/**
 * 新增供应商 查询地址
 */
export function getAddress() {
  return request({
    url: 'provider/org/getAddress',
    method: 'post',
  })
}
