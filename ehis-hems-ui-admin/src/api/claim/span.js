import request from '@/utils/request'

// 查询撤件重开
export function getList(data) {
  return request({
    url: 'claim/claim/',
    method: 'post',
    data
  })
}
